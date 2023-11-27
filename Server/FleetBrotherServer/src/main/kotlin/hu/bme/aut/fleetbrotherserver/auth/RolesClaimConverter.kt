package hu.bme.aut.fleetbrotherserver.auth

//class RolesClaimConverter(
//    private val wrappedConverter: JwtGrantedAuthoritiesConverter
//) : Converter<Jwt?, AbstractAuthenticationToken?> {
//
//    init {
//        wrappedConverter = conv
//    }
//
//    fun convert(jwt: Jwt): AbstractAuthenticationToken {
//        // get authorities from wrapped converter
//        val grantedAuthorities: ArrayList<*> = ArrayList<Any?>(wrappedConverter.convert(jwt))
//        // get role authorities
//        val roles = jwt.getClaims().get("roles") as List<String>
//        if (roles != null) {
//            for (role in roles) {
//                grantedAuthorities.add(SimpleGrantedAuthority("ROLE_$role"))
//            }
//        }
//        return JwtAuthenticationToken(jwt, grantedAuthorities)
//    }
//}