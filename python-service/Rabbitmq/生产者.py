import pika

connection = pika.BlockingConnection(pika.ConnectionParameters('43.137.12.232'))
channel = connection.channel()

#声明队列
channel.queue_declare(queue='ymx')

#发送消息
channel.basic_publish(exchange='', routing_key='ymx', body='Hello World')
print(" 发送消息: 'Hello World")
connection.close()