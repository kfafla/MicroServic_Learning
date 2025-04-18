import pika

def callback(ch, method, properties, body):
    print(f"收到：{body}")

connection = pika.BlockingConnection(pika.ConnectionParameters(host='43.137.12.232'))
channel = connection.channel()
channel.queue_declare(queue='ymx')

channel.basic_consume(queue='ymx', on_message_callback=callback, auto_ack=True)
print('等待消息。。。按CTRL+C退出')
channel.start_consuming()