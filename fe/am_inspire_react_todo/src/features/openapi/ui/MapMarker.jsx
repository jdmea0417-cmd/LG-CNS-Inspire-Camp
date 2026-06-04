import { useEffect, useRef } from "react";

const MapMarker = ({ lat, lon }) => {
    const mapRef = useRef(null);

    useEffect(() => {
        if (!lat || !lon) return;

        // 카카오 지도 스크립트가 이미 있는지 확인
        if (!window.kakao || !window.kakao.maps) {
            const script = document.createElement('script');
            let apiKey = `REDACTED_KAKAO_API_KEY`;
            script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${apiKey}&autoload=false`;
            script.async = true;
            document.head.appendChild(script);

            script.onload = () => {
                window.kakao.maps.load(() => {
                    renderMap(lat, lon);
                });
            };
        } else {
            // 이미 로드된 경우 바로 로드
            window.kakao.maps.load(() => {
                renderMap(lat, lon);
            });
        }
    }, [lat, lon]);

    const renderMap = (latitude, longitude) => {
        var container = mapRef.current;
        if (!container) return;

        const options = {
            center: new window.kakao.maps.LatLng(latitude, longitude),
            level: 3
        };

        var map = new window.kakao.maps.Map(container, options);
        var markerPosition = new window.kakao.maps.LatLng(latitude, longitude);
        var marker = new window.kakao.maps.Marker({ position: markerPosition });
        marker.setMap(map);
    };

    return (
        <div
            ref={mapRef}
            style={{
                width: '100%',
                height: '400px',
                borderRadius: '20px',
                marginTop: '20px'
            }}
        ></div>
    );
};

export default MapMarker;