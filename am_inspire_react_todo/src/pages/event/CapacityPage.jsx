

/*
Quiz
- 입장인원(10명)이 있고 

- 입장,퇴장 버튼을 만들고 
- 입장버튼은 클릭하면 인원이 증가되고 인원이 꽉차면 입장버튼을 비활성화 

- 퇴장버튼을 클릭하면 인원을 감소시키고 인원이 0이되면 퇴장버튼을 비활성화
*/
import Button from '../../components/styled/Button';
import { useState, useEffect } from 'react';

const CapacityPage = () => {
    let [cnt, setcnt] = useState(0);
    let [full, setfull] = useState(false);
    let [empty, setempty] = useState(false);
    let capacity = 10;

    const upCntHandler = (e) => {
        setcnt(cnt => cnt + 1);
        //인자가 없기 때문에, setcnt(cnt+1)로 작성해도 무방
    }
    const downCntHandler = (e) => {
        setcnt(cnt => cnt - 1)
    }

    useEffect(() => {
        console.log(`debug >>>> useEffect render cnt ${cnt}`);
        // cnt
        setfull(full => cnt >= capacity);
        setempty(empty => cnt <= 0);
        // if(cnt == capacity)
        //     alert("정원이 가득 찼습니다.");
        // if(cnt == 0)
        //     alert("퇴장할 인원이 없습니다.")
    }, [cnt]);

    useEffect(() => {
        if(full == true)
            alert("정원이 가득 찼습니다.");
    },[full])
    useEffect(() => {
        if(empty == true)
            alert("퇴장할 인원이 없습니다.");
    },[empty])
    // template UI
    return (
        <div>
            <p>입장인원 : {cnt}</p>
            <Button class="up" title="입장" onClick={upCntHandler} disabled={full} />
            <Button class="down" title="퇴장" onClick={downCntHandler} disabled={empty} />
            {
                full && <p style={{ color: 'red' }}>정원이 가득 찼습니다.</p>
            }
            {
                empty && <p style={{ color: 'red' }}>퇴장할 인원이 없습니다.</p>
            }
        </div>
    )
}

export default CapacityPage;