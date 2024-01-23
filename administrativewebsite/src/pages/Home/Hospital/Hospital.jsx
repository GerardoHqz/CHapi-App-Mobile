import React from "react";

//components
import SubMenu from "../../../components/SubMenu"
import Menu from "../../../components/Menu"
import UserCells from "../../../components/Celdas/UserCells"
import Header from "../../../components/Celdas/Header"

const Hospital = () => {
    return (
        <div className="d-flex">
            <Menu />
            <div className="w-100">
                <SubMenu linkShow="/hospital" linkNew="/newHospital"/>
                <div className="p-3 bg-white min-vh-100 max-vh-auto">
                    <div className="h-100">
                        <div className="row">
                            <div className="col-md-12 px-4 py-2">
                                <h2>Hospitales</h2>
                                <p>Lista de hospitales</p>
                                <table class="table w-100">
                                    <Header column1="#" column2="Hospital" column3="Ubicación" column4="Acción"/>
                                    <tbody>
                                        <UserCells row1="1" row2="POLICLÍNICA CASA DE SALUD, S.A. DE C.V" row3="4° AVENIDA NORTE # 3 - 5, SANTA TECLA, LA LIBERTAD"/>
                                        <UserCells row1="2" row2="HOSPITAL DE ESPECIALIDADES BRIZBAR " row3="3° CALLE PONIENTE EDIFICIO # 3 BARRIO LOS REMEDIOS, ZACATECOLUCA, LA PAZ"/>
                                        <UserCells row1="3" row2="HOSPITAL NACIONAL NUEVA CONCEPCION" row3="3a. CALLE ORIENTE, NUEVA CONCEPCIÓN, CHALATENANGO "/>
                                        <UserCells row1="4" row2="HOSPITAL SAN RAFAEL" row3="FINAL 4A. CALLE ORIENTE NO.9-2, SANTA TECLA, LA LIBERTAD"/>
                                        <UserCells row1="5" row2="HOSPITAL NACIONAL ZACAMIL" row3="CALLE LA HERMITA Y AVENIDA CASTRO MORÁN, COLONIA ZACAMIL, MIJICANOS, SAN SALVADOR"/>
                                        <UserCells row1="6" row2="HOSPITAL NACIONAL DE NIÑOS BENJAMIN BLOOM" row3="25 AVENIDA NORTE Y 29 CALLE PONIENTE, BOULEVAR DE LOS HÉROES, SAN SALVADOR, SAN SALVADOR"/>
                                        <UserCells row1="7" row2="HOSPITAL NACIONAL ROSALES" row3="FINAL CALLE ARCE Y 25 AVENIDA NORTE, SAN SALVADOR, SAN SALVADOR"/>
                                        <UserCells row1="8" row2="HOSPITAL EL SALVADOR " row3="SALÓN CENTRO AMERICANO, AV. DE LA REVOLUCION 222, SAN SALVADOR"/>
                                        <UserCells row1="9" row2="HOSPITAL SAN JUAN DE DIOS DE SAN MIGUE" row3="FINAL 11 CALLE PONIENTE #661, SAN MIGUEL, SAN MIGUEL"/>
                                        <UserCells row1="10" row2="HOSPITAL NACIONAL DE LA MUJER 'DRA. MARIA ISABEL RODRIGUEZ'" row3="ENTRE 25 AVENIDA SUR Y CALLE FRANCISCO MENENDEZ, ANTIGUA QUINTA MARIA LUISA, BARRIO SANTA ANITA, SAN SALVADOR"/>
                                        <UserCells row1="11" row2="HOSPITAL NACIONAL 'SAN PEDRO' USULUTÁN" row3="FINAL CALLE DOCTOR FEDERICO PENADO, SALIDA A SAN SALVADOR, USULUTÁN, USULUTÁN"/>                                            
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                
                </div>
                
            </div>
        </div>
    );
}

export default Hospital;