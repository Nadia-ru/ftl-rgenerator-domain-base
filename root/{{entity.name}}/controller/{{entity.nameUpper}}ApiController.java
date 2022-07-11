package {{path}}.{{entity.name}}.controller;

import {{path}}.base.api.request.SearchRequest;
import {{path}}.base.api.response.OkResponse;
import {{path}}.base.api.response.SearchResponse;
import {{path}}.{{entity.name}}.api.request.{{entity.nameUpper}}Request;
import {{path}}.{{entity.name}}.api.response.{{entity.nameUpper}}Response;
import {{path}}.{{entity.name}}.exeception.{{entity.nameUpper}}ExistException;
import {{path}}.{{entity.name}}.exeception.{{entity.nameUpper}}NotExistException;
import {{path}}.{{entity.name}}.mapping.{{entity.nameUpper}}Mapping;
import {{path}}.{{entity.name}}.routes.{{entity.nameUpper}}ApiRoutes;
import {{path}}.{{entity.name}}.service.{{entity.nameUpper}}ApiService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(value = "{{entity.nameUpper}} api")
public class {{entity.nameUpper}}ApiController {
    private final {{entity.nameUpper}}ApiService {{entity.name}}ApiService;

    @PostMapping({{entity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "Create",notes="use this when you need create new {{entity.nameUpper}}")
    @ApiResponses(value = {
           @ApiResponse(code = 200,message = "Success"),
           @ApiResponse(code = 400,message = "{{entity.nameUpper}} already exist")
    })
    public OkResponse<{{entity.nameUpper}}Response> registration(@RequestBody {{entity.nameUpper}}Request request) throws {{entity.nameUpper}}ExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponseFull().convert({{entity.name}}ApiService.create(request)));
    }

    @GetMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "find {{entity.nameUpper}} by id",notes = "use this if you need full information by {{entity.nameUpper}}")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "Success"),
            @ApiResponse(code = 404,message = "{{entity.nameUpper}} not found"),
    })
    public OkResponse<{{entity.nameUpper}}Response> byId( @ApiParam(value = "{{entity.nameUpper}} id")@PathVariable ObjectId id) throws ChangeSetPersister.NotFoundException {
    return  OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponseFull().convert(
            {{entity.name}}ApiService.findByID(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
    ));
    }
    @GetMapping({{entity.nameUpper}}ApiRoutes.ROOT)
    @ApiOperation(value = "search {{entity.nameUpper}}",notes = "use this if you need find {{entity.nameUpper}} by ????")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "Success")
    })
    public OkResponse<SearchResponse<{{entity.nameUpper}}Response>> search(
            @ModelAttribute SearchRequest request
            ){
        return  OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getSearch().convert(
                {{entity.nameUpper}}ApiService.search(request)
        ));
    }

    @PutMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "update {{entity.nameUpper}}",notes = "use this if you need update {{entity.nameUpper}}")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "Success"),
            @ApiResponse(code = 400,message = "{{entity.nameUpper}} ID invalid"),
    })
    public OkResponse<{{entity.nameUpper}}Response> updateById(
            @ApiParam(value = "{{entity.nameUpper}} id") @PathVariable String id,
            @RequestBody {{entity.nameUpper}}Request {{entity.name}}Request
            ) throws {{entity.nameUpper}}NotExistException {
        return OkResponse.of({{entity.nameUpper}}Mapping.getInstance().getResponseFull().convert(
                {{entity.name}}ApiService.update({{entity.name}}Request)
        ));
    }

    @DeleteMapping({{entity.nameUpper}}ApiRoutes.BY_ID)
    @ApiOperation(value = "delete {{entity.nameUpper}}",notes = "use this if you need delete {{entity.nameUpper}}")
    @ApiResponses(value={
            @ApiResponse(code = 200,message = "Success")
    })
    public OkResponse<String> deleteById(@ApiParam(value = "{{entity.nameUpper}} id") @PathVariable ObjectId id){
        {{entity.name}}ApiService.delete(id);
        return OkResponse.of(HttpStatus.OK.toString());
    }
}
