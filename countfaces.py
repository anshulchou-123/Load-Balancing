import cv2
import os

# Load the pre-trained Haar Cascade Classifier for face detection
face_cascade = cv2.CascadeClassifier('C:/Users/hp/OneDrive/Documents/testing/harcascade.xml')

image = cv2.imread('C:/Users/hp/OneDrive/Documents/testing/images/image.jpg')

gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
print(f"no of faces = {len(faces)}")

for (x, y, w, h) in faces:
    cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)

# Save the image with the detected faces to local storage
cv2.imwrite('C:/Users/hp/OneDrive/Documents/testing/imagesdetected/image.jpg', image)
