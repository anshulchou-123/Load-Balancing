import cv2
import os

# Capture an image from the default camera
camera = cv2.VideoCapture(0)
ret, frame = camera.read()

# If the image was captured successfully, save it
if ret:
    file_path = os.path.join('C:/Users/hp/OneDrive/Documents/testing/images', 'image.jpg')
    cv2.imwrite(file_path, frame)

camera.release()
cv2.destroyAllWindows()