package org.example;

import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import javax.swing.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int frequency = 5;
        int length = 256;

        double[] signal = generateSignal(frequency, length, "sine");

        // Подготовка к следующей степени 2
        int nextPowOf2 = 1 << (32 - Integer.numberOfLeadingZeros(length - 1));
        signal = Arrays.copyOf(signal, nextPowOf2);

        // Вычисление преобразования Фурье
        Complex[] fftResultCustom = fft(signal);
        Complex[] fftResultBuiltin = fftBuiltin(signal);

        // Построение графиков
        showGraph("Оригинальный сигнал", generateSignalDataset(signal));
        showGraph("Амплитудный спектр (Ручное FFT)", generateSpectrumDataset(fftResultCustom, nextPowOf2));
        showGraph("Амплитудный спектр (Встроенное FFT)", generateSpectrumDataset(fftResultBuiltin, nextPowOf2));
    }

    // Генерация сигнала
    private static double[] generateSignal(int frequency, int length, String signalType) {
        double[] signal = new double[length];
        for (int i = 0; i < length; i++) {
            double t = (double) i / length;
            if ("cosine".equals(signalType)) {
                signal[i] = Math.cos(2 * Math.PI * frequency * t);
            } else if ("sine".equals(signalType)) {
                signal[i] = Math.sin(2 * Math.PI * frequency * t);
            } else {
                throw new IllegalArgumentException("Invalid signal type");
            }
        }
        return signal;
    }

    // Рекурсивный алгоритм преобразования Фурье
    public static Complex[] fft(double[] input) {
        int N = input.length;
        Complex[] data = new Complex[N];
        for (int i = 0; i < N; i++) {
            data[i] = new Complex(input[i], 0);
        }
        return fftRecursive(data);
    }

    private static Complex[] fftRecursive(Complex[] data) {
        int N = data.length;
        if (N <= 1) return data;

        Complex[] even = new Complex[N / 2];
        Complex[] odd = new Complex[N / 2];

        for (int i = 0; i < N / 2; i++) {
            even[i] = data[2 * i];
            odd[i] = data[2 * i + 1];
        }

        Complex[] fftEven = fftRecursive(even);
        Complex[] fftOdd = fftRecursive(odd);

        Complex[] result = new Complex[N];
        for (int k = 0; k < N / 2; k++) {
            Complex t = Complex.fromPolar(1, -2 * Math.PI * k / N).multiply(fftOdd[k]);
            result[k] = fftEven[k].add(t);
            result[k + N / 2] = fftEven[k].subtract(t);
        }
        return result;
    }

    public static Complex[] fftBuiltin(double[] input) {
        FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
        org.apache.commons.math3.complex.Complex[] result = transformer.transform(input, TransformType.FORWARD);
        Complex[] customResult = new Complex[result.length];
        for (int i = 0; i < result.length; i++) {
            customResult[i] = new Complex(result[i].getReal(), result[i].getImaginary());
        }
        return customResult;
    }

    // Генерация данных графика для сигнала
    private static XYSeriesCollection generateSignalDataset(double[] signal) {
        XYSeries series = new XYSeries("Signal");
        for (int i = 0; i < signal.length; i++) {
            series.add(i, signal[i]);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    private static XYSeriesCollection generateSpectrumDataset(Complex[] fftResult, int N) {
        XYSeries series = new XYSeries("Amplitude Spectrum");
        for (int i = 0; i < fftResult.length; i++) {
            series.add(i, fftResult[i].magnitude());
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    private static void showGraph(String title, XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "Index",
                "Value",
                dataset
        );
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}