const container = document.querySelector('.container');
const search = document.querySelector('.search-box button');
const weatherBox = document.querySelector('.weather-box');
const weatherDetails = document.querySelector('.weather-details');
const error = document.querySelector('.not-found');

search.addEventListener('click', () => {
    const APIKey = '0f8a2c84a2e5cc4ce0cf246ecafba424';
    const city = document.querySelector('.search-box input').value;
    if (city === '') return;

    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${APIKey}`)
        .then(response => response.json())
        .then(json => {
            if (json.cod == '404'){
                container.style.height = '400px';
                weatherBox.classList.remove('active');
                weatherDetails.classList.remove('active');
                error.classList.add('active');
                return;
            }
            container.style.height = '550px';
            weatherBox.classList.add('active');
            weatherDetails.classList.add('active');
            error.classList.remove('active');

            const image = document.querySelector('.weather-box img');
            const temperature = document.querySelector('.weather-box .temparature');
            const description = document.querySelector('.weather-box .descpription');
            const humidity = document.querySelector('.weather-details .humidity span');
            const wind = document.querySelector('.weather-details .wind span');

            switch (json.weather[0].main) {
                case 'Clear':
                    image.src = 'image/clear.png';
                    break;
                case 'Rain':
                    image.src = 'image/rain.png';
                    break;
                case 'Snow':
                    image.src = 'image/snow.png';
                    break;
                case 'Mist':
                    image.src = 'image/mist.png';
                    break;
                case 'Clouds':
                    image.src = 'image/cloudy.png';
                    break;
                default:
                    image.src = 'image/clear.png';
                    break;
            }

            temperature.innerHTML = `${parseInt(json.main.temp)}<span>°C</span>`;
            description.innerHTML = `${json.weather[0].description}`;
            humidity.innerHTML = `${json.main.humidity}%`;
            wind.innerHTML = `${parseInt(json.wind.speed)}Km/h`;
        });
});

