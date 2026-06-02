

const WeatherBox = ({ weather }) => {

    // 현재 위치, 섭씨, 화씨, 날씨요약정보
    return (
        <div className="weather-box">
            <div className="city-name">{weather?.name}</div>
            <div className="temperature">{weather?.main?.temp
                ? (weather.main.temp - 273.15).toFixed(1)
                : "로딩중"}</div>
            <div className="weather-description">{weather?.weather?.[0]?.description}</div>
        </div>
    )
}

export default WeatherBox;