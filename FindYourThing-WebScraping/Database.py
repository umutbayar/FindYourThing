import mysql.connector

class Database:
    def __init__(self):
        self.connection = None

    def connect(self, host, user=None, password=None, database=None):
        if user is None:
            user = input("Kullanıcı adı: ")
        if password is None:
            password = input("Şifre: ")
        if database is None:
            database = input("Veritabanı adı: ")

        try:
            self.connection = mysql.connector.connect(
                host=host,
                user=user,
                password=password,
                database=database
            )
            print("Veritabanına başarıyla bağlandı.")
        except mysql.connector.Error as err:
            print("Hata:", err)

    def disconnect(self):
        if self.connection:
            self.connection.close()
            print("Veritabanı bağlantısı kapatıldı.")

    def insertData(self, title,poster,length,summary):
        if not self.connection:
            print("Veritabanı bağlantısı kurulmamış.")
            return
        
        cursor = self.connection.cursor()
        try:
            sql = "INSERT INTO movie (title, poster, length, summary) VALUES (%s, %s, %s, %s)"
            val = (title, poster ,length,summary)
            cursor.execute(sql, val)
            self.connection.commit()
            print("Veri başarıyla eklendi.")
        except mysql.connector.Error as err:
            print("Veri eklenirken hata:", err)
        finally:
            cursor.close()

    def insertGenre(self,title,genre_name):
        if not self.connection:
            print("Veritabanı bağlantısı kurulmamış.")
            return
        
        cursor = self.connection.cursor()
        try:
            
            sql = "INSERT INTO category (title, category) VALUES (%s, %s)"
            val = (title, genre_name)
            cursor.execute(sql, val)
            self.connection.commit()
            print("Tür başarıyla eklendi.")
        except mysql.connector.Error as err:
            print("Tür eklenirken hata:", err)
        finally:
            cursor.close()

 

  
