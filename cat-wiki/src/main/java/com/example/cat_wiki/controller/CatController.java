package com.example.cat_wiki.controller;

import com.example.cat_wiki.model.Cat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j // SLF4J Logger를 'log' 필드로 자동 생성
@Tag(name = "Cat Management", description = "고양이 정보 관리 API")
@RestController
@RequestMapping("/api/cats")
public class CatController {

    private final List<Cat> catList = new ArrayList<>();

    public CatController() {
        // 예시 데이터 추가
        catList.add(new Cat(1L, "Milo", "Siamese", 3));
        catList.add(new Cat(2L, "Luna", "Persian", 2));
        catList.add(new Cat(3L, "Leo", "Maine Coon", 4));

       log.info("Sample cats data initialized: {} cats", catList.size());
    }

    // 고양이 리스트 반환 : GET
    /*
    응답: 200 OK + 고양이 리스트 JSON
    * */
    @Operation(
            summary = "모든 고양이 조회",
            description = "등록된 모든 고양이의 정보를 조회합니다."
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "500", description = "서버 내부 오류")
    }
    )
    @GetMapping
    // http://localhost:8080/api/cats
    public ResponseEntity<List<Cat>> getAllCats(){
        // ResponseEntity.ok() = 200 OK 상태코드 + 데이터
        return ResponseEntity.ok(catList);
    }
  /*  public List<Cat> getAllCats(){
        return catList;
    }*/

    // 고양이 정보 조회 : GET
    @GetMapping("/{id}")
    // http://localhost:8080/api/cats/1
    /*
    * 응답 : 200 OK + 고양이 정보
    *       또는
    *        404  Not Found
    * */
    public ResponseEntity<Cat> getCatById(@PathVariable Long id){
        Optional<Cat> cat = catList.stream().filter(c->c.getId().equals(id)).findFirst();

        // Optional을 사용 안전한 null 처리
        if(cat.isPresent()){ // 고양이가 존재
            return ResponseEntity.ok(cat.get()); // 200 OK + 고양이 데이터
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found (본문 없음)
        }
    }
//    public Optional<Cat> getCatById(@PathVariable Long id){
    //        Optional<Cat> cat = catList.stream().filter(c->c.getId().equals(id)).findFirst();
    //        return cat;
 /*   public Cat getCatById(@PathVariable Long id){
        for (Cat cat : catList){
            if(cat.getId().equals(id)){
                return cat;
            }
        }
        return null; // 해당 ID 고양이가 없으면
    }*/

    // 새로운 고양이 추가 : POST
    @PostMapping
    // http://localhost:8080/api/cats
    public Cat addCat(@RequestBody Cat cat){
        cat.setId((long) (catList.size() + 1));
        catList.add(cat);
        return cat;
    }

    // 고양이 정보 수정 : PUT

    // 고양이 삭제 : DELETE
    // http://localhost:8080/api/cats/1
    @DeleteMapping("/{id}")
    public String deleteCat(@PathVariable Long id){
//        for(int i=0; i<catList.size(); i++){
//            if(catList.get(i).getId().equals(id)){
//                catList.remove(i);
//                return "Cat removed";
//            }
//        }
//        return "Cat not found";
        boolean removed = catList.removeIf(c->c.getId().equals((id)));
        return removed ? "Cat removed" : "Cat not found";
    }
}
