from src.boolean_function import BooleanFunction
from src.utils import glue_terms  # Предполагаемый импорт вашей функции


class CalculationMinimizer:
    def __init__(self, function: BooleanFunction):
        self.function = function
        self.variables = ''.join(self.function.variables)  # Строковое представление для glue_terms
        self.initial_terms = self.function.terms.copy()  # Копия исходных термов
        self.is_dnf = self.function.is_dnf  # True для СДНФ, False для СКНФ

    def minimize(self):
        terms = self.initial_terms.copy()
        prev_terms = []
        stages = []
        stage = 0
        while set(terms) != set(prev_terms):
            stage += 1
            if stage > 100:
                print("Прервано: слишком много итераций")
                break
            prev_terms = terms.copy()
            terms = self._perform_gluing(terms)
            stages.append(terms.copy())
        if stages and stages[-1] == terms:
            stages.pop()

        # Находим существенно необходимые импликанты и минимальное покрытие
        final_implicants = sorted(terms.copy())
        essential_implicants = self._find_essential_implicants(final_implicants)
        remaining_terms = [t for t in self.initial_terms if not any(self._covers(e, t) for e in essential_implicants)]
        remaining_implicants = [i for i in final_implicants if i not in essential_implicants]

        # Минимальное покрытие оставшихся термов
        while remaining_terms and remaining_implicants:
            best_implicant = None
            max_coverage = 0
            for imp in remaining_implicants:
                covered = sum(1 for t in remaining_terms if self._covers(imp, t))
                if covered > max_coverage or (covered == max_coverage and imp and len(imp) < len(best_implicant or "")):
                    max_coverage = covered
                    best_implicant = imp
            if best_implicant:
                essential_implicants.append(best_implicant)
                remaining_implicants.remove(best_implicant)
                remaining_terms = [t for t in remaining_terms if not self._covers(best_implicant, t)]
            else:
                break

        result = self._format_result(essential_implicants)
        return {"stages": stages, "result": result}

    def _find_essential_implicants(self, implicants):
        essential = []
        covered_terms = set()
        for term in self.initial_terms:
            covering_implicants = [imp for imp in implicants if self._covers(imp, term)]
            if len(covering_implicants) == 1 and covering_implicants[0] not in essential:
                essential.append(covering_implicants[0])
                covered_terms.add(term)
        return sorted(essential)

    def _perform_gluing(self, terms):
        new_terms = set()
        used_terms = set()
        for i, term1 in enumerate(terms):
            for term2 in terms[i + 1:]:
                result = glue_terms(term1, term2, self.variables)
                if result:
                    new_terms.add(result)
                    used_terms.add(term1)
                    used_terms.add(term2)
        remaining_terms = set(terms) - used_terms
        new_terms.update(remaining_terms)
        return list(new_terms)

    def _is_redundant(self, implicant, current_implicants):
        remaining_implicants = [imp for imp in current_implicants if imp != implicant]
        if not remaining_implicants:
            return False
        for term in self.initial_terms:
            if not any(self._covers(other_imp, term) for other_imp in remaining_implicants):
                return False
        return True

    def _covers(self, implicant, original_term):
        imp_bin = self.function.to_binary(implicant)
        term_bin = self.function.to_binary(original_term)
        for bit1, bit2 in zip(imp_bin, term_bin):
            if not (bit1 == bit2 or bit1 == "X"): return False
        return True

    def _format_result(self, terms):
        if self.is_dnf:
            cleaned_terms = []
            for term in terms:
                term = term.replace("∧", "").replace("∨∨", "∨")
                if term:
                    literals = [lit for lit in term.split("∨") if lit]
                    cleaned_term = "∧".join(literals)
                    cleaned_terms.append(f"{cleaned_term}")
            return " ∨ ".join(cleaned_terms) if cleaned_terms else "0"
        else:
            formatted_terms = []
            for term in terms:
                # Убираем лишние ∨ и ∧
                term = term.replace("∨∨", "∨").replace("∧", "")
                literals = []
                i = 0
                while i < len(term):
                    if term[i] == "¬":
                        literals.append(term[i:i + 2])  # Берем ¬ и букву
                        i += 2
                    elif term[i].isalpha():
                        literals.append(term[i])  # Берем одиночную букву
                        i += 1
                    else:
                        i += 1  # Пропускаем лишние символы
                literals = [lit for lit in literals if lit]  # Убираем пустые литералы
                if literals:
                    disjunction = "∨".join(literals)
                    formatted_terms.append(f"({disjunction})")
            return " ∧ ".join(formatted_terms) if formatted_terms else "1"