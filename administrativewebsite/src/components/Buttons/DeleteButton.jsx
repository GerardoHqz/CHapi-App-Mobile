import React from "react";
import { IconContext } from "react-icons";
import { MdDeleteOutline } from "react-icons/md"

const DeleteButton = () => {
    return (
        <button className="bg-transparent border-0">
        <IconContext.Provider value={{ size: "1.5em", className: "me-2" }}>
            <MdDeleteOutline />
        </IconContext.Provider>
    </button>
    );
}

export default DeleteButton;