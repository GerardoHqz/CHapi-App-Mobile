import react from 'react';
import { useNavigate } from "react-router-dom";

const SubMenu = (props) => {

    const navigate = useNavigate();

    const onClickHandler = async (e,link) => {
        console.log("si")
        e.preventDefault();
        navigate(link);
    }

    return (
        <div className="w-100">
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-xl">
                    <div className="collapse navbar-collapse" id="navbarsExample07XL">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item active">
                                <a role="button" className="nav-link cursor-pointer" onClick={e=>onClickHandler(e,props.linkShow)}>Ver</a>
                            </li>
                            <li className="nav-item">
                                <a role="button" className="nav-link cursor-pointer" onClick={e=>onClickHandler(e,props.linkNew)}>Nuevo</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    );
}

export default SubMenu;