package wordolina.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import wordolina.dao.JdbcDao;
import wordolina.service.DatabaseService;
import wordolina.service.PropertyService;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private JdbcDao jdbcDao;

    @RequestMapping("/main")
    public ModelAndView mainPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mainPage");
        String score = PropertyService.getScore();
        String word = jdbcDao.getWord();
        String[] name_translate = word.split(" = ");
        List<String> translates = DatabaseService.getRandomTranslates(name_translate[1]);
        translates = DatabaseService.removeDublicates(translates);
        modelAndView.addObject("score", score);
        modelAndView.addObject("word", name_translate[0]);
        modelAndView.addObject("answer", null);
        for(int i = 0; i < translates.size(); i++){
            modelAndView.addObject("translate" + i, translates.get(i));
        }
        return modelAndView;
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public ModelAndView answerPage(@RequestParam("answerTranslate") String answer,
                                   @RequestParam("word") String word){
        ModelAndView modelAndView = new ModelAndView();
        boolean testResult = new JdbcDao().checkWord(word, answer);
        List<String> name_translate = jdbcDao.getTranslate(word);
        if(testResult){
            modelAndView.addObject("result", "Правильно!");
            modelAndView.addObject("realAnswer", "");
            PropertyService.addPositiveNumber();
        } else {
            modelAndView.addObject("result", "Неправильно!");
            modelAndView.addObject("realAnswer", "Правильна відповідь: " +
                    name_translate.get(0) + " - " + name_translate.get(1));
            PropertyService.addNegativeNumber();
        }
        modelAndView.addObject("score", PropertyService.getScore());
        modelAndView.setViewName("answerPage");
        return modelAndView;
    }

    @RequestMapping("")
    public String nullify(){
        PropertyService.nullifyScore();
        return "redirect:/main";
    }

    //todo: do something with answer bug
    //todo: make nullify first request mapping
}
