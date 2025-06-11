package org.scoula.ex03.dto;
import lombok.Data;
@Data
public class SampleDTO {

    private String name;
    private int age;

    //getters/setters 메소드를 통해서 가방에 데이터를 넣거나 꺼낸다
    // 가방에 넣을때는 setName(), setAge()를 이용
    // 가방에서 꺼낼때는 getName() getAge()를 이용.
    //핸들러어댑터가 이 가방에 http로 전달된 데이터를 넣어주는데
    //set메서드를 자동으로 넣어줌
    //set메서드가 없으면 가방에 못넣음, 에러발생!!
}
