import react from 'react';

const Botton = (props) => {
    return (
        <button type={props.type} class={props.nameClass}>
            {props.text}
        </button>
    );
}

export default Botton;
