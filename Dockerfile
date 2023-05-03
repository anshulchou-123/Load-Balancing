# Base image
FROM python:3.9-alpine

# Set the working directory in the container
WORKDIR /code

# Copy the requirements.txt file and install the dependencies
COPY ./requirements.txt /code/requirements.txt
RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

# Copy the rest of the application files to the container
COPY ./src/main/python-files/get-image-from-db.py /code

# Expose the port that the Flask application will run on
EXPOSE 5000

# Start the Flask application
CMD ["python", "get-image-from-db.py"]
