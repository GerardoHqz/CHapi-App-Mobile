import React from "react";
import { useNavigate } from "react-router-dom"
import { useState } from "react";
//components
import SubMenu from "../../../components/SubMenu"
import Menu from "../../../components/Menu"


const NewMedicine = () => { 
    const navigate = useNavigate();

    const onSubmitHandler = async (e) => {
        e.preventDefault();

        navigate("/medicine");
    }


    return (
        <div>
            <div className="d-flex">
                <Menu />

                <div className="w-100">
                    <SubMenu linkShow="/medicine" linkNew="/newmedicine" />
                    <section className="p-3 bg-white h-100" id="content">
                        <div className="container">
                            <div className="row">
                                <div className="col-md-12 m-3">
                                    <h2>Medicamentos</h2>
                                    Agregar un nuevo medicamento

                                    <form onSubmit={onSubmitHandler} className="me-3 my-3 shadow p-5 mb-5 bg-body rounded">
                                        <div class="row mb-3">
                                            <label for="formGroupExampleInput" class="col-sm-2 col-form-label">Nombre</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" id="formGroupExampleInput" />
                                            </div>
                                        </div>

                                        <fieldset class="row mb-3">
                                            <legend class="col-form-label col-sm-2 pt-0">Tipo</legend>
                                            <div class="col-sm-10">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" />
                                                    <label class="form-check-label" for="flexRadioDefault1">
                                                        Pastilla
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked />
                                                    <label class="form-check-label" for="flexRadioDefault2">
                                                        Jarabe
                                                    </label>
                                                </div>
                                                <div class="form-check">
                                                    <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault3" checked />
                                                    <label class="form-check-label" for="flexRadioDefault3">
                                                        Inyección
                                                    </label>
                                                </div>
                                            </div>
                                        </fieldset>

                                        <button type="submit" class="btn btn-primary">Guardar</button>
                                    </form>                                   
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>

            
        </div>
    );
}

export default NewMedicine;