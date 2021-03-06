# ความต้องการ
* ระบบจัดการหัวข้อการสัมนา

# วัตถุประสงค์
* เพื่อช่วยอำนวยความสะดวกในการจัดการหัวข้อการสัมนาที่อาจจะผ่านการคัดกรองมาบ้างแล้วแต่ก็ยังยากที่จะจัดให้ลงตัวได้ในช่วงวันที่จำกัด เราจึงมาช่วยแก้ปัญหาในการจัดการเรื่องเหล่านี้

# วิธีใช้งานเบื้องต้น

1. นำไฟล์ input เข้าไปไว้ที่ folder /src/SampleFiles โดยจะต้องเป็นไฟล์ชนิด text หรือ .txt เท่านั้น<br />
2. ในไฟล์ input ในชื่อหัวข้อสัมนาจะไม่มีตัวเลข และช่วงเวลาของแต่ละหัวข้อสัมนาจะมีหน่วยเป็นนาทีเท่านั้น
3. ใส่ชื่อไฟล์ของ input ใน path ที่ตัวแปร fileToRead ในไฟล์ Main.java ใน package train03<br />
<img width="489" alt="Screenshot 2022-03-04 112116" src="https://user-images.githubusercontent.com/84006033/156698939-91868ef8-b5c0-49f9-91f5-d14e6792ce4e.png"><br />
3. ทดลอง Run โปรแกรมว่าเกิด Error หรือไม่<br />
4. ถ้าไม่เกิดข้อผิดพลาด ในfolder /src/SampleFiles จะมีไฟล์ .txt ปรากฏขึ้นในชื่อ วัน-เดือน-ปี-ชื่อไฟล์<br />

ตัวอย่างผลลัพธ์: <br />
<img width="462" alt="Screenshot 2022-03-04 112212" src="https://user-images.githubusercontent.com/84006033/156699415-678b58bd-f137-4372-84d8-ceabf922f31d.png"><br />

# แผนผัง
**Flowchart**<br />
![flowchart](https://user-images.githubusercontent.com/84006033/156701578-733a42d1-756a-400d-b8b1-f220e1aaaa9b.png)<br />
**Sequence Diagram**<br />
![new diagram (1)](https://user-images.githubusercontent.com/84006033/158523866-13fd7d92-b51f-47a4-a99e-bd1105258ef9.png)

