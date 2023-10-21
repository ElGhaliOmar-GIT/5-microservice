package tn.esprit.pidev.bns.service.ibtihel;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.ICreateCode;

@Service
public class CreateCodeService implements ICreateCode {
    @Override
    public String createCode() {

            int length = 5;
            return RandomString.make(length);

    }
}
