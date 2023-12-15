function showWeather() {
        const container = document.querySelector('.container');
        const search = document.querySelector('.search-box button');
        const weatherBox = document.querySelector('.weather-box');
        const weatherDetails = document.querySelector('.weather-details');
        const error = document.querySelector('.not-found');


        container.style.height = '400px';
        weatherBox.classList.remove('active');
        weatherDetails.classList.remove('active');
        error.classList.add('active');

        container.style.height = '550px';
        weatherBox.classList.add('active');
        weatherDetails.classList.add('active');
        error.classList.remove('active');

}