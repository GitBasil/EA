package mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class CalcController {
    @PostMapping("/calc")
    public ResponseEntity<?> calculate(@RequestBody Calculation calculation) {
        System.out.println("operation = " + calculation.getOperation());
        double result = 0.0;

        switch (calculation.getOperation()) {
            case "+": {
                result = calculation.getNumber1() + calculation.getNumber2();
                break;
            }
            case "-": {
                result = calculation.getNumber1() - calculation.getNumber2();
                break;
            }
            case "*": {
                result = calculation.getNumber1() * calculation.getNumber2();
                break;
            }
            case "/": {
                result = calculation.getNumber1() / calculation.getNumber2();
                break;
            }
        }
        CalculationResult calculationResult = new CalculationResult(calculation.getNumber1(), calculation.getNumber2(), calculation.getOperation(), result);
        return new ResponseEntity<CalculationResult>(calculationResult, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<String>("aaaa", HttpStatus.OK);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleExceptions(Exception exception) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("isSuccess", false);
//        map.put("error", exception.getMessage());
//        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}



