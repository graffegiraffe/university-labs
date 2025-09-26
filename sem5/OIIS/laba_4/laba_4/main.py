import numpy as np
import cv2
import matplotlib.pyplot as plt

# Гауссово размытие
def custom_gaussian_blur(image, kernel_size, sigma):
    kernel = np.zeros((kernel_size, kernel_size), np.float32)
    half_size = kernel_size // 2
    for x in range(-half_size, half_size + 1):
        for y in range(-half_size, half_size + 1):
            kernel[x + half_size, y + half_size] = (1 / (2 * np.pi * sigma ** 2)) * np.exp(
                -(x ** 2 + y ** 2) / (2 * sigma ** 2))
    kernel /= kernel.sum()

    # Применение фильтра
    height, width = image.shape
    blurred = np.zeros_like(image, dtype=np.float32)
    for i in range(half_size, height - half_size):
        for j in range(half_size, width - half_size):
            region = image[i - half_size:i + half_size + 1, j - half_size:j + half_size + 1]
            blurred[i, j] = np.sum(region * kernel)

    return blurred.astype(np.uint8)


# Функция Канни
def custom_canny(image, low_threshold, high_threshold):
    # Градиенты с помощью Собеля
    sobel_x = np.array([[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]])
    sobel_y = np.array([[-1, -2, -1], [0, 0, 0], [1, 2, 1]])

    gradient_x = cv2.filter2D(image, -1, sobel_x)
    gradient_y = cv2.filter2D(image, -1, sobel_y)

    magnitude = np.hypot(gradient_x, gradient_y)

    # Нормализация градиентов
    magnitude = magnitude / magnitude.max() * 255
    magnitude = magnitude.astype(np.uint8)

    # Определение порогов
    edges = np.zeros_like(magnitude, dtype=np.uint8)
    strong = magnitude > high_threshold
    weak = (magnitude >= low_threshold) & (magnitude <= high_threshold)

    edges[strong] = 255
    edges[weak] = 100  # Выделение слабых границ для дальнейшей обработки

    return edges

image = cv2.imread('giraffe.jpg', cv2.IMREAD_GRAYSCALE)

blurred_image = custom_gaussian_blur(image, kernel_size=5, sigma=1)

edges = custom_canny(blurred_image, low_threshold=100, high_threshold=200)

plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.title('Оригинальное изображение')
plt.imshow(image, cmap='gray')

plt.subplot(1, 2, 2)
plt.title('Обработанное изображение')
plt.imshow(edges, cmap='gray')
plt.show()
