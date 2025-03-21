from fastapi import FastAPI, Request, HTTPException
from fastapi.responses import StreamingResponse
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import io
import json

app = FastAPI()

@app.post("/generate_wordcloud")
async def generate_wordcloud(request: Request):
    data = await request.json()
    text = data.get("text")
    if not text:
        raise HTTPException(status_code=400, detail="No text provided")

    # 生成词云
    wordcloud = WordCloud(width=800, height=400, background_color="white").generate(text)

    # 将词云图保存到内存
    img = io.BytesIO()
    wordcloud.to_image().save(img, format="PNG")
    img.seek(0)

    return StreamingResponse(io.BytesIO(img.getvalue()), media_type="image/png")

@app.post("/generate_bar_chart")
async def generate_bar_chart(request: Request):
    data = await request.json()
    labels = data.get("labels")
    values = data.get("values")

    if not labels or not values:
        raise HTTPException(status_code=400, detail="Invalid data provided")

    fig, ax = plt.subplots()
    ax.bar(labels, values)
    ax.set_xlabel("Labels")
    ax.set_ylabel("Values")
    ax.set_title("Bar Chart")

    img = io.BytesIO()
    plt.savefig(img, format="PNG")
    img.seek(0)
    plt.close(fig)

    return StreamingResponse(io.BytesIO(img.getvalue()), media_type="image/png")

@app.post("/generate_line_chart")
async def generate_line_chart(request: Request):
    data = await request.json()
    labels = data.get("labels")
    values = data.get("values")

    if not labels or not values:
        raise HTTPException(status_code=400, detail="Invalid data provided")

    fig, ax = plt.subplots()
    ax.plot(labels, values, marker='o')
    ax.set_xlabel("Labels")
    ax.set_ylabel("Values")
    ax.set_title("Line Chart")

    img = io.BytesIO()
    plt.savefig(img, format="PNG")
    img.seek(0)
    plt.close(fig)

    return StreamingResponse(io.BytesIO(img.getvalue()), media_type="image/png")