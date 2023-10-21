package tn.esprit.pidev.bns.service.ibtihel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.ibtihel.CommandLine;
import tn.esprit.pidev.bns.repository.ibtihel.CommandLineRepo;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.ICommandeLine;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CommandeLineService implements ICommandeLine {
    @Autowired
    CommandLineRepo commandLineRepo;



    // ////commandLine //////
    @Override
    public CommandLine addCommandLine(CommandLine commandLine) {

        return commandLineRepo.save(commandLine);
    }


    @Override
    public CommandLine updateCommandLine(CommandLine commandLine) {

        return commandLineRepo.save(commandLine);
    }

    @Override
    public CommandLine deleteCommandLine(Integer idCommandLine) {
        commandLineRepo.deleteById(idCommandLine);
        return null;
    }

    @Override
    public List<CommandLine> ListCommandLine() {

        return  (List<CommandLine>) commandLineRepo.findAll();
    }

    @Override
    public CommandLine ListCommanLineById(Integer idCommandLine) {
        return commandLineRepo.findById(idCommandLine).get();
    }








}
