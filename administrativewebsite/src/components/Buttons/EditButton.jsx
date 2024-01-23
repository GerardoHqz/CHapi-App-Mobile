import React from "react";
import { IconContext } from "react-icons";
import { MdOutlineEdit } from "react-icons/md"


const EditButton = () => {
    return (
        <button className="bg-transparent border-0">
        <IconContext.Provider value={{ size: "1.5em", className: "me-2" }}>
            <MdOutlineEdit />
        </IconContext.Provider>
    </button>
    );
}

export default EditButton;