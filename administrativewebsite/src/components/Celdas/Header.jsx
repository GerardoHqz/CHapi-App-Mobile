import react from 'react';

const Header = (props) => {
    return (
        <thead>
        <tr>
            <th scope="col">{props.column1}</th>
            <th scope="col">{props.column2}</th>
            <th scope="col">{props.column3}</th>
            <th scope="col">{props.column4}</th>

        </tr>
    </thead>
        
    );
}

export default Header;
