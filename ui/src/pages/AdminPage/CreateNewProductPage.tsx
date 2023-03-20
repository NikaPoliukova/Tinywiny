import React, {useEffect, useRef, useState} from 'react'
import {Form, Header, Segment} from 'semantic-ui-react'
import {Button, Container} from "@mui/material";
import MyHeader from 'pages/component/MyHeader';
import {TypeProduct} from "../../model/TypeProduct";
import TypeProductService from "../../services/TypeProductService";
import ProductService from "../../services/ProductService";
import ImageService from "../../services/ImageService";
import {useNavigate} from "react-router-dom";

export function CreateProduct() {
    const [image, setImage] = useState('');
    const [productName, setProductName] = useState('');
    const [price, setPrice] = useState(0);
    const [countInStock, setCountInStock] = useState(0);
    const [description, setDescription] = useState('');
    const [idType, setIdType] = useState(0);
    const fileInput = useRef<HTMLInputElement | null>(null);
    const [types, setTypes] = useState<Array<TypeProduct>>([]);
    const navigate = useNavigate();

    useEffect(() => {
        TypeProductService.findAllTypes()
            .then(response => setTypes(response))
           .then(() => ImageService.getImage()
                .then(response => {
                    setImage(response)
                }))
    }, []);

    const options = types.map((type, idType) => {
        return <option key={idType} value={idType}>{type.name}</option>;
    });

    const uploadProduct = () => {
        ProductService.uploadFile({
            productName, price, countInStock, description, idType,
            file: fileInput?.current?.files && fileInput?.current?.files[0]
        })
            .then(response => {
                setImage(response);
            }).then(() => navigate("/admin"))
    };

    return (
        <>
            <MyHeader/>
            <Container>
                <Segment placeholder>
                    <Header icon>
                        <img src={`data:image/png;base64,${image}`}/>
                    </Header>
                    <Button variant="contained"
                            component="label">
                        Add photo
                        <input
                            ref={fileInput}
                            type="file"
                            hidden/>
                    </Button>
                </Segment>
                <Form>
                    <Form.Group widths='equal'>
                        <Form.Input fluid label='Product Name' placeholder='Product Name'
                                    value={productName}
                                    onChange={e => setProductName(e.target.value)}/>
                    </Form.Group>
                    <div>Type product</div>
                    <Form.Group widths='equal'>
                        <select value={idType} onChange={event => setIdType(Number(event.target.value))}>
                            {options}
                        </select>
                    </Form.Group>
                    <Form.Group widths='equal'>
                        <Form.Input fluid label='Price' placeholder='Price' value={price}
                                    onChange={e => setPrice(Number(e.target.value))}/>
                    </Form.Group>
                    <Form.Group widths='equal'>
                        <Form.Input fluid label='Count in stock' placeholder='Count in stock'
                                    value={countInStock}
                                    onChange={e => setCountInStock(Number(e.target.value))}/>
                    </Form.Group>
                    <Form.Group widths='equal'>
                    </Form.Group>
                    <Form.TextArea label='Discription' placeholder='Write dicscription...'
                                   value={description}
                                   onChange={e => setDescription(e.target.value)}/>
                    <Button
                        variant="contained"
                        component="label"
                        onClick={uploadProduct}>
                        Save product
                    </Button>
                </Form>
            </Container>
        </>
    )
}