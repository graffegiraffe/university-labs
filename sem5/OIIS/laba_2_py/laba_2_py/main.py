import cv2
from matplotlib import pyplot as plt

image = cv2.imread('giraffe.jpg', cv2.IMREAD_COLOR)

median_filtered = cv2.medianBlur(image, 11)

gaussian_blur = cv2.GaussianBlur(image, (9, 9), 0)

sobel_x = cv2.Sobel(image, cv2.CV_64F, 1, 0, ksize=5)

laplacian = cv2.Laplacian(image, cv2.CV_64F)

canny_edges = cv2.Canny(image, 100, 200)

filters = [image, median_filtered, gaussian_blur, sobel_x, laplacian, canny_edges]
filter_titles = ['Original', 'Median Filter', 'Gaussian Blur', 'Sobel X', 'Laplacian', 'Canny Edges']

plt.figure(figsize=(10, 8))
for i in range(len(filters)):
    plt.subplot(2, 4, i + 1)
    plt.imshow(filters[i], cmap='gray')
    plt.title(filter_titles[i])
    plt.axis('off')

plt.tight_layout()
plt.show()
