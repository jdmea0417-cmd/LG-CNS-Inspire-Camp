import { Button } from "react-bootstrap";

const WeatherButton = ({ cities, setCity }) => {
    console.log(`debug >>>> cities button : ${cities}`)

    /*
    Quiz
    - 버튼의 이벤트가 발생되었을 때
    - 버튼의 도시이름(값)을 전달하여 해당 도시의 현재 날씨정보를 받아서
    - 화면에 렌더링하는 작업
    */
    return (
        <div className="button-group">
            {
                cities.map((city, idx) => {
                    return (
                        <Button className='btn'
                            key={idx}
                            onClick={() => setCity(city)}>
                            {city}
                        </Button>
                    )
                })
            }
        </div>
    );
}

export default WeatherButton;