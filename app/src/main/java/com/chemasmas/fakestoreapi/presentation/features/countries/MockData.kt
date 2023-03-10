package com.chemasmas.fakestoreapi.presentation.features.countries

import com.chemasmas.fakestoreapi.core.data.repository.models.DetailedCountry
import com.chemasmas.fakestoreapi.core.data.repository.models.SimpleCountry

val mockSimpleCountryList = listOf(
    SimpleCountry(name = "Andorra", capital = "Andorra la Vella", code = "AD", emoji = "🇦🇩"),
    SimpleCountry(
        name = "United Arab Emirates",
        capital = "Abu Dhabi",
        code = "AE",
        emoji = "🇦🇪"
    ),
    SimpleCountry(name = "Afghanistan", capital = "Kabul", code = "AF", emoji = "🇦🇫"),
    SimpleCountry(
        name = "Antigua and Barbuda",
        capital = "Saint John's",
        code = "AG",
        emoji = "🇦🇬"
    ),
    SimpleCountry(name = "Anguilla", capital = "The Valley", code = "AI", emoji = "🇦🇮"),
    SimpleCountry(name = "Albania", capital = "Tirana", code = "AL", emoji = "🇦🇱"),
    SimpleCountry(name = "Armenia", capital = "Yerevan", code = "AM", emoji = "🇦🇲"),
    SimpleCountry(name = "Angola", capital = "Luanda", code = "AO", emoji = "🇦🇴"),
    SimpleCountry(name = "Antarctica", capital = "null", code = "AQ", emoji = "🇦🇶"),
    SimpleCountry(name = "Argentina", capital = "Buenos Aires", code = "AR", emoji = "🇦🇷"),
    SimpleCountry(name = "American Samoa", capital = "Pago Pago", code = "AS", emoji = "🇦🇸"),
    SimpleCountry(name = "Austria", capital = "Vienna", code = "AT", emoji = "🇦🇹"),
    SimpleCountry(name = "Australia", capital = "Canberra", code = "AU", emoji = "🇦🇺"),
    SimpleCountry(name = "Aruba", capital = "Oranjestad", code = "AW", emoji = "🇦🇼"),
    SimpleCountry(name = "Åland", capital = "Mariehamn", code = "AX", emoji = "🇦🇽"),
    SimpleCountry(name = "Azerbaijan", capital = "Baku", code = "AZ", emoji = "🇦🇿"),
    SimpleCountry(
        name = "Bosnia and Herzegovina",
        capital = "Sarajevo",
        code = "BA",
        emoji = "🇧🇦"
    ),
    SimpleCountry(name = "Barbados", capital = "Bridgetown", code = "BB", emoji = "🇧🇧"),
    SimpleCountry(name = "Bangladesh", capital = "Dhaka", code = "BD", emoji = "🇧🇩"),
    SimpleCountry(name = "Belgium", capital = "Brussels", code = "BE", emoji = "🇧🇪"),
    SimpleCountry(name = "Burkina Faso", capital = "Ouagadougou", code = "BF", emoji = "🇧🇫"),
    SimpleCountry(name = "Bulgaria", capital = "Sofia", code = "BG", emoji = "🇧🇬"),
    SimpleCountry(name = "Bahrain", capital = "Manama", code = "BH", emoji = "🇧🇭"),
    SimpleCountry(name = "Burundi", capital = "Bujumbura", code = "BI", emoji = "🇧🇮"),
    SimpleCountry(name = "Benin", capital = "Porto-Novo", code = "BJ", emoji = "🇧🇯"),
    SimpleCountry(name = "Saint Barthélemy", capital = "Gustavia", code = "BL", emoji = "🇧🇱"),
    SimpleCountry(name = "Bermuda", capital = "Hamilton", code = "BM", emoji = "🇧🇲"),
    SimpleCountry(name = "Brunei", capital = "Bandar Seri Begawan", code = "BN", emoji = "🇧🇳"),
    SimpleCountry(name = "Bolivia", capital = "Sucre", code = "BO", emoji = "🇧🇴"),
    SimpleCountry(name = "Bonaire", capital = "Kralendijk", code = "BQ", emoji = "🇧🇶"),
    SimpleCountry(name = "Brazil", capital = "Brasília", code = "BR", emoji = "🇧🇷"),
    SimpleCountry(name = "Bahamas", capital = "Nassau", code = "BS", emoji = "🇧🇸"),
    SimpleCountry(name = "Bhutan", capital = "Thimphu", code = "BT", emoji = "🇧🇹"),
    SimpleCountry(name = "Bouvet Island", capital = "null", code = "BV", emoji = "🇧🇻"),
    SimpleCountry(name = "Botswana", capital = "Gaborone", code = "BW", emoji = "🇧🇼"),
    SimpleCountry(name = "Belarus", capital = "Minsk", code = "BY", emoji = "🇧🇾"),
    SimpleCountry(name = "Belize", capital = "Belmopan", code = "BZ", emoji = "🇧🇿"),
    SimpleCountry(name = "Canada", capital = "Ottawa", code = "CA", emoji = "🇨🇦"),
    SimpleCountry(
        name = "Cocos [Keeling] Islands",
        capital = "West Island",
        code = "CC",
        emoji = "🇨🇨"
    ),
    SimpleCountry(
        name = "Democratic Republic of the Congo",
        capital = "Kinshasa",
        code = "CD",
        emoji = "🇨🇩"
    ),
    SimpleCountry(
        name = "Central African Republic",
        capital = "Bangui",
        code = "CF",
        emoji = "🇨🇫"
    ),
    SimpleCountry(
        name = "Republic of the Congo",
        capital = "Brazzaville",
        code = "CG",
        emoji = "🇨🇬"
    ),
    SimpleCountry(name = "Switzerland", capital = "Bern", code = "CH", emoji = "🇨🇭"),
    SimpleCountry(name = "Ivory Coast", capital = "Yamoussoukro", code = "CI", emoji = "🇨🇮"),
    SimpleCountry(name = "Cook Islands", capital = "Avarua", code = "CK", emoji = "🇨🇰"),
    SimpleCountry(name = "Chile", capital = "Santiago", code = "CL", emoji = "🇨🇱"),
    SimpleCountry(name = "Cameroon", capital = "Yaoundé", code = "CM", emoji = "🇨🇲"),
    SimpleCountry(name = "China", capital = "Beijing", code = "CN", emoji = "🇨🇳"),
    SimpleCountry(name = "Colombia", capital = "Bogotá", code = "CO", emoji = "🇨🇴"),
    SimpleCountry(name = "Costa Rica", capital = "San José", code = "CR", emoji = "🇨🇷"),
    SimpleCountry(name = "Cuba", capital = "Havana", code = "CU", emoji = "🇨🇺"),
    SimpleCountry(name = "Cape Verde", capital = "Praia", code = "CV", emoji = "🇨🇻"),
    SimpleCountry(name = "Curacao", capital = "Willemstad", code = "CW", emoji = "🇨🇼"),
    SimpleCountry(
        name = "Christmas Island",
        capital = "Flying Fish Cove",
        code = "CX",
        emoji = "🇨🇽"
    ),
    SimpleCountry(name = "Cyprus", capital = "Nicosia", code = "CY", emoji = "🇨🇾"),
    SimpleCountry(name = "Czech Republic", capital = "Prague", code = "CZ", emoji = "🇨🇿"),
    SimpleCountry(name = "Germany", capital = "Berlin", code = "DE", emoji = "🇩🇪"),
    SimpleCountry(name = "Djibouti", capital = "Djibouti", code = "DJ", emoji = "🇩🇯")
)

val mockSimpleCountry =
    SimpleCountry(name = "Andorra", capital = "Andorra la Vella", code = "AD", emoji = "🇦🇩")

val mockDetailedCountry = DetailedCountry(
    code = "AD",
    name = "Andorra",
    emoji = "🇦🇩",
    capital = "Andorra la Vella",
    currency = "EUR",
    languages = listOf("Catalan"),
    continent = "Europe"
)