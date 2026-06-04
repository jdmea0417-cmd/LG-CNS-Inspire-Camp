import WeatherBox from "./ui/WeatherBox";
import WeatherButton from "./ui/WeatherButton";
import MapMarker from "./ui/MapMarker";

import './css/weather.css';
import { useEffect, useState } from "react";


const OpenApiWeatherPage = () => {
    const cities = ["Seoul", "Busan", "Gwangju", "Daegu"];
    const [city, setCity] = useState();
    const [weather, setWeather] = useState();
    const [lat, setLat] = useState();
    const [lon, setLon] = useState();

    // 현재 위치정보 가져오기
    const getCurrentLocation = () => {
        navigator.geolocation.getCurrentPosition((position) => {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            setLat(latitude);
            setLon(longitude);
            getCurrentWeather(latitude, longitude)
        })
    }
    // 통신 openapi server
    // fetch api 활용
    const getCurrentWeather = async (latitude, longitude) => {
        let apiKey = `5203704cde58933d05ab51e292c0204a`;
        let endpoint = `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${apiKey}`
        console.log(latitude, longitude)
        try {
            let response = await fetch(endpoint)
            let weatherInfo = await response.json()
            console.log(`debug >>>> fetch response`);
            console.log(weatherInfo);
            setWeather(weatherInfo);
        }
        catch (err) {
            console.log(err);
        }
    }

    // 해당 도시 값이 전달되었을 때 날씨정보를 가져오는 구현
    const getCityWeather = async () => {
        let apiKey = `5203704cde58933d05ab51e292c0204a`;
        let endpoint = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`
        try {
            let response = await fetch(endpoint)
            let weatherInfo = await response.json()
            const latitude = weatherInfo.coord.lat;
            const longitude = weatherInfo.coord.lon;
            setLat(latitude);
            setLon(longitude);
            console.log(`debug >>>> ${city} : weatherInfo`);
            console.log(weatherInfo);
            setWeather(weatherInfo);
        }
        catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        getCurrentLocation();
    }, [])

    useEffect(() => {
        getCityWeather();
    }, [city])

    return (
        <div className="container">
            <WeatherBox weather={weather} />
            <WeatherButton cities={cities} setCity={setCity} />
            <MapMarker lat={lat} lon={lon} />
        </div>
    );
}

export default OpenApiWeatherPage;