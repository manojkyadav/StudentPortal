LearnDark - Spring Boot + Thymeleaf + MongoDB (Dark theme)

Run:
1. Start MongoDB locally: mongodb://127.0.0.1:27017
2. mvn clean package
3. java -jar target/student-platform-mongo-1.0.0.jar

Create admin (Mongo shell):
1) Generate a bcrypt hash (or use the register endpoint and then update role to ADMIN in DB)
2) Insert document:
   db.students.insertOne({ name: 'Admin', email: 'admin@local', passwordHash: '<bcrypt-hash>', role: 'ADMIN' })

If you want, I can add a seed script to auto-create demo data and an admin account.
