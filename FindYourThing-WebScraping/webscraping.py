import os
import requests
from bs4 import BeautifulSoup
from Database import Database

db = Database()
db.connect("localhost")

base_url = 'https://www.themoviedb.org/movie?page={}&language=en-US'

headers = {'User-Agent': 'Mozilla/5.0'}

page_number = 1
filmsayısı = 1
while True:
    url = base_url.format(page_number)
    response = requests.get(url, headers=headers)

    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')

        h2_tags = soup.find_all('h2')

        if not h2_tags:
            print('Sayfa', page_number, 'bulunamadı. Çıkılıyor.')
            break

        for h2_tag in h2_tags:
            a_tag = h2_tag.find('a')
            if not a_tag:
                continue
            filmsayısı += 1
            title = a_tag['title']
            href = a_tag['href']
            movie_url = 'https://www.themoviedb.org{}?language=en-US'
            full_url = movie_url.format(href)
            response_genres = requests.get(full_url, headers=headers)
            if response_genres.status_code == 200:
                soup_movie = BeautifulSoup(response_genres.text, 'html.parser')
                genres_span = soup_movie.find('span', class_='genres')
                length_span = soup_movie.find('span', class_='runtime')
                if length_span:
                 length = length_span.get_text(strip=True)
                summary_span=soup_movie.find('div', class_='overview').find('p') 
                summary= summary_span.get_text(strip=True)
                if genres_span:
                    genre_links = genres_span.find_all('a')
                  
                    genres_list = []
                    for genre_link in genre_links:
                        genre_name = genre_link.text
                       
                        genres_list.append(genre_name)     
                else:
                    print('deneme')

                img_url = soup_movie.find('img', class_='poster')['src']
            
                db.insertData(title,img_url,length,summary)     
                for genre_name in genres_list:
                    db.insertGenre(title,genre_name)
        page_number += 1
    else:
        print('Hata! Sayfa', page_number, 'indirilemedi.')
        break

db.disconnect()
