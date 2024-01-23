import React from "react";
import { useNavigate } from "react-router-dom";
//components
import SubMenu from "../../../components/SubMenu"
import Menu from "../../../components/Menu"

const NewHospital = () => {
    const navigate = useNavigate();

    const onSubmitHandler = async (e) => {
        e.preventDefault();

        navigate("/hospital");
    }

    return (
        <div className="d-flex">
            <Menu />
            <div className="w-100">
                <SubMenu linkShow="/hospital" linkNew="/newhospital" />
                <section className="p-3 bg-white min-vh-100 max-vh-auto">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-12 ">
                                <h2>Hospitales</h2>
                                <p>Agregar nuevo hospital</p>
                                <form onSubmit={onSubmitHandler} className="my-3 me-4 shadow px-5 pb-5 pt-4 bg-body rounded">
                                    <div class="form-group mb-3">
                                        <label for="formGroupExampleInput">Hospital</label>
                                        <input type="text" class="form-control" id="formGroupExampleInput" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="exampleInputPassword1">Dirección</label>
                                        <input type="password" class="form-control" id="exampleInputPassword1" />
                                    </div>                                  
                                    <button type="submit" class="btn btn-primary">Guardar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </section>
            </div >
        </div >

    );
}

export default NewHospital;