package org.g9.challenge.aluralatam.miconversordemonedas;

import java.util.List;

public record CodigoDeMonedas(String result,
                              String documentation,
                              String terms_of_use,
                              List<List<String>> supported_codes) {
}
