package org.g9.challenge.aluralatam.miconversordemonedas;

public record Moneda(String result,
                     String documentation,
                     String terms_of_use,
                     String time_last_update_unix,
                     String time_last_update_utc,
                     String time_next_update_unix,
                     String time_next_update_utc,
                     String base_code,
                     String target_code,
                     String conversion_rate,
                     String conversion_result) {}
