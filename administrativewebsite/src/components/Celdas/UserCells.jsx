import react from 'react';

import DeleteButton from "../Buttons/DeleteButton";
import EditButton from "../Buttons/EditButton";

const UserCells = (props) => {
    return (
            <tr>
                <th scope="row">{props.row1}</th>
                <td>{props.row2}</td>
                <td>{props.row3}</td>
                <td>
                    <DeleteButton />
                    <EditButton />
                </td>
            </tr>
        
    );
}

export default UserCells;
