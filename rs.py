import requests
import io
import time
import picamera
import base64
import RPi.GPIO as GPIO

camera = picamera.PiCamera()



# GPIO 설정

GPIO.setmode(GPIO.BCM)

GPIO.setup(20, GPIO.OUT)  # 첫 번째 LED

GPIO.setup(16, GPIO.OUT)  # 두 번째 LED

GPIO.setup(21, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)  # 스위치



def capture():

    path = "1.jpg"
    camera.capture(path)
    return path

def detect(path):

    google_url = 'https://content-vision.googleapis.com/v1/images:annotate'
    key = 
    f = open(path, 'rb')
    img_base64 = base64.b64encode(f.raw.readall()).decode()

    data = {
        "requests": [
            {
                "features": [
                    {
                        "maxResults": 100,
                        "type": "OBJECT_LOCALIZATION"
                    }
                ],
                "image": {
                    "content": img_base64      
                }
            }
        ]
    }

    resp = requests.post(url=f"{google_url}?alt=json&key={key}", json=data)
    return resp.json()


def main():

    while True:
# 스위치가 위로 클릭되면 불빛 켜짐
        if GPIO.input(21):
            GPIO.output(20, GPIO.HIGH)  # 첫 번째 LED 켜짐
            GPIO.output(16, GPIO.HIGH)  # 두 번째 LED 켜짐
# 스위치가 아래 방향으로 내려올 때까지 대기
while GPIO.input(21):
    time.sleep(0.1)
     # LED 끄기
    GPIO.output(20, GPIO.LOW)  # 첫 번째 LED 끔
    GPIO.output(16, GPIO.LOW)  # 두 번째 LED 끔

       # 10초 대기 후 사진 찍기    
    time.sleep(10)

    path = capture()
    result = detect(path)

    print(result)


if __name__ == "__main__":

    try:
        main()
    finally:

 # 프로그램이 종료되면 GPIO 핀 정리
        GPIO.cleanup()
