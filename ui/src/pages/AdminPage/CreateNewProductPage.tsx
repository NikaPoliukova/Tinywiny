import React, {useEffect, useState} from 'react'
import {Form, Header, Icon, Segment} from 'semantic-ui-react'
import {IconButton} from "@mui/material";
import {AddAPhoto} from "@mui/icons-material";
import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";
import {useNavigate} from 'react-router-dom';
import MyHeader from 'pages/component/MyHeader';
import {Image} from "../../model/Image";
import ImageService from "../../services/ImageService";
import {TypeProduct} from "../../model/TypeProduct";
import TypeProductService from "../../services/TypeProductService";

//выводить сообщение,если не корректные параметры

export function CreateProduct() {

    const [productName, setProductName] = useState('');
    const [price, setPrice] = useState(Number);
    const [countInStock, setCountInStock] = useState(Number);
    const [description, setDescription] = useState('');
    const [typeProduct, setType] = useState<TypeProduct>();
    const [idType, setIdType] = useState(Number);
    const [productId, setProductId] = useState(0);
    const [imageName, setImageName] = useState('');
    const [types, setTypes] = useState<Array<TypeProduct>>([]);

    useEffect(() => {
        TypeProductService.findAllTypes()
            .then(response => setTypes(response));
    }, []);

    const options = types.map((type, idType) => {
        return <option key={idType} value={idType}>{type.name}</option>;
    });
    const navigate = useNavigate();
    const addProduct = () => {
        const product: Product = {
            productName,
            price,
            countInStock,
            description,
            idType
        };
        ProductService.createProduct(product).then(response => navigate("/products"));
    }
    const addImage = () => {
        const image: Image = {
            imageName,
            productId
            //не могу указать айди продукта-не создан
        }
        ImageService.updateImage(image).then(response => navigate("/products"));
    }

    return (
        <>
            <MyHeader/>
            <Segment placeholder>
                <Header icon>
                    <Icon name='photo'/>
                </Header>
                <IconButton color="primary"
                            aria-label="upload picture"
                            component="label"
                            onClick={addImage}
                >
                    <input hidden accept="image/*" type="file"
                        // value={file}
                        // onChange={e => setFile(e.target.value)}
                    />
                    <AddAPhoto/>
                </IconButton>
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

                <Form.Button
                    onClick={addProduct}
                >Add product</Form.Button>
            </Form>
        </>
    )
}