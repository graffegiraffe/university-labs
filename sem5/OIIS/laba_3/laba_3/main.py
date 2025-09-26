import cv2
from matplotlib import pyplot as plt

def equalize_image(image):
    if len(image.shape) == 2:  # Если изображение черно-белое
        return cv2.equalizeHist(image)
    else:
        img_yuv = cv2.cvtColor(image, cv2.COLOR_BGR2YUV)
        # Выравниваем (Y)
        img_yuv[:, :, 0] = cv2.equalizeHist(img_yuv[:, :, 0])
        return cv2.cvtColor(img_yuv, cv2.COLOR_YUV2BGR)

image1 = cv2.imread('giraffe.jpg')
image2 = cv2.imread('night.jpg')

equalized_image1 = equalize_image(image1)
equalized_image2 = equalize_image(image2)

images = [image1, equalized_image1, image2, equalized_image2]
titles = ['Original Image 1', 'Equalized Image 1', 'Original Image 2', 'Equalized Image 2']

plt.figure(figsize=(10, 8))

for i in range(4):
    plt.subplot(2, 2, i + 1)
    plt.imshow(cv2.cvtColor(images[i], cv2.COLOR_BGR2RGB))
    plt.title(titles[i])
    plt.xticks([]), plt.yticks([])

plt.tight_layout()
plt.show()
