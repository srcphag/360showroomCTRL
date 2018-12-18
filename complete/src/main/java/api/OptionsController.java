package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;

@RestController
public class OptionsController {

    private Options opt = new Options();
    private OSCController osc = new OSCController();

    @RequestMapping("/options")
    public String infoToJson() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(opt);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


    @GetMapping("/update")
    public @ResponseBody
    String updateOptions(@Valid @RequestParam String id, @RequestParam String value) {

        if (opt != null) {
            if (id != null) {
                //Lamada por modulo OSC
                osc.doSendOn(id,value);
                update(id, value);
                return "{\n" + " \"status\" : " + HttpStatus.OK + ",\n" + " \"message\" : \"Book updated successfully\"\n" + "}";
            } else {
                return "{\n" + " status : " + HttpStatus.BAD_REQUEST + "," + "\n" + " message : Missing parameters" + "\n" + "}";
            }

        } else {

            return "{\n" + "status : " + HttpStatus.BAD_REQUEST + "," + "\n" + "message : could not find the book desired" + "\n" + "}";
        }
    }

    private void update(@Valid String id, String value) {
        Field field = null;
        try {
            field = Options.class.getField(id);
            field.set(opt, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
