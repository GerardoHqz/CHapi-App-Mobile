import React from "react";

//components
import SubMenu from "../../../components/SubMenu"
import Menu from "../../../components/Menu"
import UserCells from "../../../components/Celdas/UserCells"
import Header from "../../../components/Celdas/Header"

const Medicine = () => {
    return (
        <div className="d-flex">
            <Menu />
            <div className="w-100">
                <SubMenu linkShow="/medicine" linkNew="/newMedicine"/>
                <div className="p-3 bg-white min-vh-100 max-vh-auto">
                    <div className="h-100">
                        <div className="row">
                            <div className="col-md-12 px-4 py-2">
                                <h2>Medicamentos</h2>
                                <p>Lista de medicamentos</p>
                                <table class="table w-100">
                                    <Header column1="#" column2="Nombre de medicamento" column3="Tipo de medicamento" column4="Acción"/>
                                    <tbody>
                                        <UserCells row1="1" row2="Acetaminofen" row3="Pastillas"/>
                                        <UserCells row1="2" row2="Acetaminofen" row3="Jarabe"/>
                                        <UserCells row1="3" row2="Vitaminas complejo B" row3="Inyección"/>
                                        <UserCells row1="4" row2="Vitaminas complejo B" row3="Pastillas"/>
                                        <UserCells row1="5" row2="Ibuprofeno" row3="Jarabe"/>
                                        <UserCells row1="6" row2="Loratadina" row3="Pastillas"/>
                                        <UserCells row1="7" row2="Loratadina" row3="Jarabe"/>
                                        <UserCells row1="8" row2="Amoxixilina" row3="Pastillas"/>
                                        <UserCells row1="9" row2="Insulina" row3="Inyección"/>
                                        <UserCells row1="10" row2="Penisilina" row3="Inyección"/>
                                        <UserCells row1="11" row2="Doloneurobion" row3="Pastilla"/>    
                                        <UserCells row1="12" row2="Virogrip" row3="Pastilla"/>                                        
                                        
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

export default Medicine;