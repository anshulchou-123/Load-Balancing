from flask import Flask, jsonify

import cv2
import os
import numpy as np
import mysql.connector

app = Flask(__name__)


@app.route('/get_count_of_image', methods=['GET'])
def get_count_of_image():
    # Establish database connection
    mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="anshul@123",
    database="major")

    # Create cursor object
    mycursor = mydb.cursor()

    # Execute SQL query to retrieve image from database
    mycursor.execute("SELECT image FROM images WHERE id = 140")

    # Retrieve image data from query result
    image_binary = mycursor.fetchone()[0]

    # Convert the binary representation of the image to a NumPy array
    image_array = np.frombuffer(image_binary, np.uint8)
    image = cv2.imdecode(image_array, cv2.IMREAD_COLOR)

    # Load the pre-trained Haar Cascade Classifier for face detection
    face_cascade = cv2.CascadeClassifier(
        'C:/Users/hp/OneDrive/Documents/testing/harcascade.xml')

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

    faces = face_cascade.detectMultiScale(
        gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
    print(f"no of faces = {len(faces)}")

    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)

    # Save the image with the detected faces to local storage
    cv2.imwrite(
        'C:/Users/hp/OneDrive/Documents/testing/imagesdetected/image.jpg', image)

    # Code to retrieve count of image from db in OpenCv
    count = len(faces)
    return jsonify({'count': count})

if __name__ == '__main__':
    app.run(host='127.0.0.1', port=5000)
