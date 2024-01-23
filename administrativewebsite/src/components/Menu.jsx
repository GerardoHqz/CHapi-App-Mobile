import React from "react";
import { useNavigate } from "react-router-dom";
//assets
import side from "../assets/sideBar.module.css";
import user from "../assets/img/user.png";
//icons
import { BiUser } from "react-icons/bi";
import { BiCapsule } from "react-icons/bi";
import { RiHospitalLine } from "react-icons/ri";
import { IconContext } from "react-icons";

const Menu = () => {
    const navigate = useNavigate();

    const onCLickHandler = async (e,link) => {
        e.preventDefault();
        navigate(link);
    }

    return (
        <div className={side.sidebar}>
            <div className="p-2 bg-transparent text-center">
                <img src={user} className="img-fluid px-5 pt-3" />
                <h4 className="mt-3">Administrador</h4>
                <a role ="button" className="nav-link text-white mb-3" href="/login"> Cerrar sesión</a>
            </div>
            <div id="sidebar-accordion">
                <div className="list-group p-2">
                    <button className={side.btn} onClick={e=>onCLickHandler(e,"/users") }>
                        <IconContext.Provider value={{ size: "1.5em", className: "me-2" }}>
                            <BiUser />
                        </IconContext.Provider>
                        Usuario</button>
                    <button className={side.btn} onClick={e=>onCLickHandler(e,"/medicine") }>
                        <IconContext.Provider value={{ size: "1.5em", className: "me-2" }}>
                            <BiCapsule />
                        </IconContext.Provider>
                        Medicamentos</button>

                    <button className={side.btn} onClick={e=>onCLickHandler(e,"/hospital") }>
                        <IconContext.Provider value={{ size: "1.5em", className: "me-2" }}>
                            <RiHospitalLine />
                        </IconContext.Provider>
                        Hospitales</button>
                </div>
            </div>
        </div>
    );
}

export default Menu;