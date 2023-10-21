package tn.esprit.pidev.bns.serviceInterface.ibtihel;

import tn.esprit.pidev.bns.entity.ibtihel.CommandLine;

import java.util.List;

public interface ICommandeLine {

    //commandline
    public CommandLine addCommandLine(CommandLine commandLine);
    public CommandLine updateCommandLine(CommandLine commandLine);
    public CommandLine deleteCommandLine(Integer idCommandLine);
    List<CommandLine> ListCommandLine();

    public CommandLine ListCommanLineById(Integer idCommandLine);
}
