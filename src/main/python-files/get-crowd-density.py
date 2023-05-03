from flask import Flask, jsonify

app = Flask(__name__)

@app.route('/get_crowd_density', methods=['GET'])
def get_crowd_density():
    # Code to retrieve crowd-density
    count = 5
    return jsonify({'count': count})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)