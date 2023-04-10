FROM python:3.11.2

WORKDIR /code

COPY ./requirementsforir.txt /code/requirements.txt

# RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

# RUN apt-get update && \
#     apt-get install -y libatlas-base-dev python3-pyqt5 libsm6 libxext6 libxrender-dev v4l-utils && \
#     rm -rf /var/lib/apt/lists/*

RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

RUN apt-get update && \
    apt-get install -y libatlas-base-dev python3-pyqt5 libsm6 libxext6 libxrender-dev v4l-utils && \
    rm -rf /var/lib/apt/lists/*

COPY ./webcam.py /code

CMD ["python", "webcam.py"]
