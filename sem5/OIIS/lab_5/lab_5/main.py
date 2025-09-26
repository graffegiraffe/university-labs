from PIL import Image

def create_stereoscopic_image(left_image_path, right_image_path, output_path):
    left_image = Image.open(left_image_path).convert("RGB")
    right_image = Image.open(right_image_path).convert("RGB")

    if left_image.size != right_image.size:
        print("Изображения должны быть одинакового размера!")
        return

    left_r, left_g, left_b = left_image.split()
    right_r, right_g, right_b = right_image.split()

    # Создаем новое изображение, комбинируя красный канал левого и синий/зеленый каналы правого
    stereoscopic_image = Image.merge("RGB", (left_r, right_g, right_b))

    stereoscopic_image.save(output_path)
    print(f"Стереоскопическое изображение сохранено в {output_path}")

left_image_path = "left.jpg"
right_image_path = "right.jpg"
output_path = "stereoscopic_image.jpg"

create_stereoscopic_image(left_image_path, right_image_path, output_path)
