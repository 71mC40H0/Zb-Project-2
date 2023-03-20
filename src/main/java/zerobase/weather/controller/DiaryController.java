package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 데이터를 저장합니다")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "저장할 일기의 날짜", example = "2023-03-20") LocalDate date,
                     @RequestBody @ApiParam("입력할 일기 내용") String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 날짜", example = "2023-03-20") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간 중의 모든 일기 데이터를 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째 날짜", example = "2023-03-20") LocalDate startDate,
                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "조회할 기간의 마지막 날짜", example = "2023-03-20") LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 첫 일기 데이터를 새로 입력한 일기 데이터로 교체합니다")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "수정할 일기의 날짜", example = "2023-03-20") LocalDate date,
                     @RequestBody @ApiParam("새로 입력할 일기 내용") String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 삭제합니다")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @ApiParam(value = "삭제할 일기의 날짜", example = "2023-03-20") LocalDate date) {
        diaryService.deleteDiary(date);
    }

}
