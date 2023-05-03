import base64
from flask import Flask, send_file

import cv2
import os
import numpy as np
import mysql.connector

wifi_ip="192.168.43.117"
app = Flask(__name__)


@app.route('/get_image_from_db/<int:user_id>', methods=['GET'])
def get_image_from_db(user_id):

    # Establish database connection
    mydb = mysql.connector.connect(
        host=wifi_ip,
        user="anshulchou123",
        password="anshul@123",
        database="major")
    
    # Create cursor object
    mycursor = mydb.cursor()
    # Execute SQL query to retrieve image from database
    mycursor.execute('SELECT image FROM images WHERE id = %s',(user_id,))
    # Retrieve image data from query result
    image_binary = mycursor.fetchone()[0]
    print(len(image_binary))

    # Convert the binary representation of the image to a NumPy array
    image_array = np.frombuffer(image_binary, np.uint8)
    image = cv2.imdecode(image_array, cv2.IMREAD_COLOR)

    image_b64 = base64.b64encode(image_binary).decode('utf-8')

    # return send_file(image_binary, mimetype='image/jpeg', as_attachment=False)
    return image_b64
if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0', port=5000)
