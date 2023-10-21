package tn.esprit.pidev.bns.controller.ibtihel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.ibtihel.CommandLine;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.ICommandeLine;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/CommandeLineController")

public class CommandeLineController {


    @Autowired
    ICommandeLine iCommandeLine;



    @PostMapping("/addCommandLine")
    public CommandLine addCommandLine(CommandLine commandLine) {
        System.out.println(commandLine.getQuantity());
        return iCommandeLine.addCommandLine(commandLine);
    }


    @PutMapping("/updateCommandLine")
    public CommandLine updateCommandLine(CommandLine commandLine) {
        return iCommandeLine.updateCommandLine(commandLine);
    }


    @PutMapping("/deleteCommandLine/{id}")
    public CommandLine deleteCommandLine(@PathVariable("id") Integer idCommandLine) {
        return iCommandeLine.deleteCommandLine(idCommandLine);
    }



    @GetMapping("/GetListCommandLine")
    public List<CommandLine> ListCommandLine() {
        return  iCommandeLine.ListCommandLine();
    }



    @GetMapping("/GetListCommandLineById/{id}")
    public CommandLine ListCommanLineById(@PathVariable("id") Integer idCommandLine) {
        return iCommandeLine.ListCommanLineById(idCommandLine);
    }




}
