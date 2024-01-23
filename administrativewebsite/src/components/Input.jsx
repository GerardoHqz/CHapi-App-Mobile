import react from 'react';

const Input =({type="text", placeholder,value,onChange,name})=>{
    return(
        <div>
            <input type={type} placeholder={placeholder}  value={value} onChange={onChange} name={name}
            className="form-control mt-1"/>
        </div>
    )
}

export default Input;